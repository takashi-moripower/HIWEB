package logistics.system.project.common.form;

import java.util.List;

import javax.validation.Valid;

import logistics.system.project.common.Entity.AnkenDetailEntity;
import logistics.system.project.common.Entity.NohinEntity;
import logistics.system.project.common.Entity.SyukaEntity;
import logistics.system.project.common.Entity.TruckEntity;

public class AnkenDetailForm {
	@Valid
	private AnkenDetailEntity ankenDetail;
	private TruckEntity truck;
	private List<SyukaEntity> syukaList;
	private List<NohinEntity> nohinList;

	public AnkenDetailEntity getAnkenDetail() {
		return ankenDetail;
	}

	public void setAnkenDetail(AnkenDetailEntity ankenDetail) {
		this.ankenDetail = ankenDetail;
	}

	public TruckEntity getTruck() {
		return truck;
	}

	public void setTruck(TruckEntity truck) {
		this.truck = truck;
	}

	public List<SyukaEntity> getSyukaList() {
		return syukaList;
	}

	public void setSyukaList(List<SyukaEntity> syukaList) {
		this.syukaList = syukaList;
	}

	public List<NohinEntity> getNohinList() {
		return nohinList;
	}

	public void setNohinList(List<NohinEntity> nohinList) {
		this.nohinList = nohinList;
	}

}
