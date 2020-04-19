package Folder;

public class Server {
	public int id;
	public String type;
	public int limit;
	public int bootupTime;
	public float rate;
	public int coreCount;
	public int memory;
	public int disk;

	public Server(int id, String t, int l, int b, float r, int c, int m, int d) {
		this.id = id;
		this.type = t;
		this.limit = l;
		this.bootupTime = b;
		this.rate = r;
		this.coreCount = c;
		this.memory = m;
		this.disk = d;
	}
}