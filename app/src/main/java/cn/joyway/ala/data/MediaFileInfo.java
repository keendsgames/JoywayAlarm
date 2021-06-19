package cn.joyway.ala.data;

/**
 * 
 * @ClassName: MediaFileInfo
 * @Description: TODO(ý���ļ���Ϣ)
 * @author varyawp2015@gmail.com
 * @date 2015-5-7 ����11:11:09
 * 
 */
public class MediaFileInfo {

	String dates, path, name, time;

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public MediaFileInfo(String dates, String path, String name, String time) {
		super();
		this.dates = dates;
		this.path = path;
		this.name = name;
		this.time = time;
		setDates(dates);
		setName(name);
		setPath(path);
		setTime(time);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
