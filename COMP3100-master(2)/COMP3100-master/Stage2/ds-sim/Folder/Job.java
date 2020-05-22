package Folder;

public class Job {
 
    public int bootupTime;
    public int id;
    public int estRuntime;
    public int coreCount;
    public int memory;
    public int disk;
 
    public Job(int st, int id, int r, int c, int m, int d) {
        this.bootupTime = st;
        this.id = id;
        this.estRuntime = r;
        this.coreCount = c;
        this.memory = m;
        this.disk = d;
    }
}
