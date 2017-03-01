package bk.acs.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koteswarao on 01-03-2017.
 */

public class Data {
    public static String sample[]={"First subject", "second subject","third subject","fourth subject","fifth subject","subject"};
    public static List<ListItem> list=new ArrayList<>();
    public static List getList()
    {
        for(int i=0;i<sample.length;i++)
        {
            ListItem listItem=new ListItem(sample[i],(i+1)+""); //Fixed crash
            list.add(listItem);
        }
        return list;
    }
}
