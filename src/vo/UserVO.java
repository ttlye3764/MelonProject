package vo;

public class UserVO {
	
	private String u_id;
	private String u_pw;
	private String u_name;
	private String u_n_name;
	private int u_ticket;
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVO other = (UserVO) obj;
		if (u_id == null) {
			if (other.u_id != null)
				return false;
		} else if (!u_id.equals(other.u_id))
			return false;
		if (u_name == null) {
			if (other.u_name != null)
				return false;
		} else if (!u_name.equals(other.u_name))
			return false;
		if (u_pw == null) {
			if (other.u_pw != null)
				return false;
		} else if (!u_pw.equals(other.u_pw))
			return false;
		return true;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getU_pw() {
		return u_pw;
	}
	public void setU_pw(String u_pw) {
		this.u_pw = u_pw;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_n_name() {
		return u_n_name;
	}
	public void setU_n_name(String u_n_name) {
		this.u_n_name = u_n_name;
	}
	public int getU_ticket() {
		return u_ticket;
	}
	public void setU_ticket(int u_ticket) {
		this.u_ticket = u_ticket;
	}






}
