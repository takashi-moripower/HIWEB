package logistics.system.project.utility;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 */
public class PagerTag extends TagSupport {
	private static final long serialVersionUID = 5729832874890369508L;
	private String url; // URI
	private int pageSize = 5; //
	private int pageNo = 1; //
	private int recordCount; //

	public int doStartTag() throws JspException {
		int pageCount = (recordCount + pageSize - 1) / pageSize; //

		// html　output
		StringBuilder sb = new StringBuilder();

		sb.append("<style type=\"text/css\">");
		sb.append(".pagination {padding: 5px;float:right;font-size:12px; margin: 0px;}");
		sb.append(".pagination a, .pagination a:link, .pagination a:visited {padding:2px 5px;margin:2px;border:1px solid #aaaadd;text-decoration:none;color:#006699;}");
		sb.append(".pagination a:hover, .pagination a:active {border: 1px solid #ff0000;color: #000;text-decoration: none;}");
		sb.append(".pagination span.current {padding: 2px 5px;margin: 2px;border: 1px solid #ff0000;font-weight: bold;background-color: #ff0000;color: #FFF;}");
		sb.append(".pagination span.disabled {padding: 2px 5px;margin: 2px;border: 1px solid #eee; color: #ddd;}");
		sb.append("</style>\r\n");
		sb.append("<div class=\"pagination\">\r\n");
		if (recordCount == 0) {
			sb.append("<strong>検索した件数が０です。</strong>\r\n");
		} else {
			if (pageNo > pageCount) {
				pageNo = pageCount;
			}
			if (pageNo < 1) {
				pageNo = 1;
			}

			sb.append("<form name=\"pageForm\" method=\"GET\" action=\"").append(this.url).append("\">\r\n");

			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			Enumeration<String> enumeration = request.getParameterNames();
			String name = null;
			String value = null;

			while (enumeration.hasMoreElements()) {
				name = enumeration.nextElement();
				value = request.getParameter(name);

				if (name.equals("pageNo")) {
					if (null != value && !"".equals(value)) {
						pageNo = Integer.parseInt(value);
					}
					continue;
				}
				sb.append("<input type=\"hidden\" name=\"").append(name).append("\" value=\"")
						.append(value).append("\"/>\r\n");
			}

			sb.append("<input type=\"hidden\" name=\"").append("pageNo").append("\" value=\"")
					.append(pageNo).append("\"/>\r\n");

			sb.append("<ul class=\"pagination\" style=\"margin:0px;\">");
			if (pageNo == 1) {
				sb.append("<li><span class=\"disabled\">&nbsp;&lt;&lt;").append("</span></li>\r\n");
				sb.append("<li><span class=\"disabled\">&nbsp;&lt;").append("</span></li>\r\n");
			} else {
				sb.append("<li><a href=\"javascript:turnOverPage(")
						.append(((pageNo - 5) < 1) ? 1 : (pageNo - 5))
						.append(")\">&nbsp;&lt;&lt;</a></li>\r\n");

				sb.append("<li><a href=\"javascript:turnOverPage(").append(pageNo - 1)
						.append(")\">&nbsp;&lt;</a></li>\r\n");
			}

			// from 1 , and display "..."
			int start = 1;
			if (this.pageNo > 4) {
				start = this.pageNo - 1;
				sb.append("<li><a href=\"javascript:turnOverPage(1)\">1</a></li>\r\n");
				sb.append("<li><a href=\"javascript:turnOverPage(2)\">2</a></li>\r\n");
				sb.append("<li><span class=\"disabled\">&hellip;</span></li>\r\n");
			}

			// near current pageNo
			int end = this.pageNo + 1;
			if (end > pageCount) {
				end = pageCount;
			}
			for (int i = start; i <= end; i++) {
				if (pageNo == i) { // no hyperlink
					sb.append("<li><span class=\"current\">").append(i).append("</span></li>\r\n");
				} else {
					sb.append("<li><a href=\"javascript:turnOverPage(").append(i).append(")\">")
							.append(i).append("</a></li>\r\n");
				}
			}
			// To end, and display"..."
			if (end < pageCount - 2) {
				sb.append("<li><span class=\"disabled\">&hellip;</span></li>\r\n");
			}
			if (end < pageCount - 1) {
				sb.append("<li><a href=\"javascript:turnOverPage(").append(pageCount - 1)
						.append(")\">").append(pageCount - 1).append("</a></li>\r\n");
			}
			if (end < pageCount) {
				sb.append("<li><a href=\"javascript:turnOverPage(").append(pageCount).append(")\">")
						.append(pageCount).append("</a></li>\r\n");
			}

			// next
			if (pageNo == pageCount) {
				sb.append("<li><span class=\"disabled\">&gt;&nbsp;").append("</span></li>\r\n");
				sb.append("<li><span class=\"disabled\">&gt;&gt;&nbsp;").append("</span></li>\r\n");
			} else {
				sb.append("<li><a href=\"javascript:turnOverPage(").append(pageNo + 1)
						.append(")\">&gt;&nbsp;</a></li>\r\n");
				sb.append("<li><a href=\"javascript:turnOverPage(")
						.append(((pageNo + 5) > pageCount) ? pageCount : (pageNo + 5))
						.append(")\">&gt;&gt;&nbsp;</a></li>\r\n");
			}
			sb.append("</ul>");
			sb.append("</form>\r\n");

			// request form JS
			sb.append("<script language=\"javascript\">\r\n");
			sb.append("  function turnOverPage(no){\r\n");
			sb.append("    if(no>").append(pageCount).append("){");
			sb.append("      no=").append(pageCount).append(";}\r\n");
			sb.append("    if(no<1){no=1;}\r\n");
			sb.append("    document.pageForm[0].pageNo.value=no;\r\n");
			sb.append("    document.pageForm[0].submit();\r\n");
			sb.append("  }\r\n");
			sb.append("</script>\r\n");
		}
		sb.append("</div>\r\n");

		// output response
		try {
			pageContext.getOut().println(sb.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
}