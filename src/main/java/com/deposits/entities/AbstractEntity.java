
//cant make foreign key to refer to Id from parent class

/*package com.deposits.entities;

import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity {

	@Id
	@GeneratedValue()
	private long id;
	
	public long getId () {
		return id;
	}
	
	public void setId (long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals (Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		return Objects.equals(this.id, AbstractEntity.class.cast(obj).id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}*/
