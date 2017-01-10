package logistics.system.project.tuchi.dao;

import java.util.List;

public interface RelationDao<KEY,VALUE> {

	public String getTableName();

	public List<VALUE> getValues(KEY key);

	public void save( KEY key , List<VALUE> values );
}
