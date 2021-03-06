package groups.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.avaje.ebean.annotation.CreatedTimestamp;

@Entity
@Table(name = "groups_group")
public class Group {

	public enum GroupType {
		INCLUDE,
		EXCLUDE
	}
	
	public enum GroupStatus {
		ENABLED,
		DISABLED,
		DISCIPLINED,
		DELETED
	}
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", unique = true, nullable = false, length = 30)
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "group")
	@MapKey(name = "memberName")
	private Map<String, GroupMember> groupMembers = new HashMap<String, GroupMember>();
	
	@Column(name = "personal", nullable = false)
	private Boolean personal = false;
	
	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "type", nullable = false, length = 2)
	private GroupType type = GroupType.INCLUDE;
	
	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "status", nullable = false, length = 2)
	private GroupStatus status = GroupStatus.ENABLED;
	
	@Column(name = "password", nullable = true, length = 16)
	private String password;
	
	@Version
	@Column(name = "update_time", nullable = false)
    Timestamp updatetime;
	
	@CreatedTimestamp
	@Column(name = "create_time", nullable = false)
    Timestamp createTime;

	public Group() {}
	
	public Group(String name) {
		this.name = name;
	}
	
	public Group(Integer id, String name, Boolean personal) {
		this.id = id;
		this.name = name;
		this.personal = personal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNormalizedName() {
		return name.toLowerCase();
	}
	
	public Boolean getPersonal() {
		return personal;
	}

	public void setPersonal(Boolean personal) {
		this.personal = personal;
	}
	
	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	public GroupType getType() {
		return type;
	}

	public void setType(GroupType type) {
		this.type = type;
	}
	
	public GroupStatus getStatus() {
		return status;
	}
	
	public void setStatus(GroupStatus status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	
	public GroupMember getGroupMember(String username) {
		return groupMembers.get(username);
	}

	public Map<String, GroupMember> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(Map<String, GroupMember> groupMembers) {
		this.groupMembers = groupMembers;
	}

	public void addGroupMember(GroupMember groupMember) {
		groupMembers.put(groupMember.getMemberName(), groupMember);
	}
	
	public void removeGroupMemmber(GroupMember groupMember) {
		groupMembers.remove(groupMember.getMemberName());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", groupMembers="
				+ groupMembers + ", personal=" + personal + ", type=" + type
				+ ", status=" + status + ", password=" + password
				+ ", updatetime=" + updatetime + ", createTime=" + createTime
				+ "]";
	}
}
