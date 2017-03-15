package bk.acs.RecyclerView;

/**
 * Created by koteswarao on 01-03-2017.
 */

public class ListItem {
    String subName;
    String sno;

    public ListItem(String subName, String sno)//int to string stopped from crashing
    {
        this.subName = subName;
        this.sno = sno;
    }
}
