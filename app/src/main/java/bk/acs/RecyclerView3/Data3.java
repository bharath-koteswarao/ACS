package bk.acs.RecyclerView3;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bk on 07-03-2017.
 */

public class Data3 {
    Context context;
    public List<ListItem3> list=new ArrayList<>();
    public Data3(Context ctx)
    {
        context=ctx;
    }
    public List getList()
    {
        for(int i=0;i<20;i++)
        {
            list.add(new ListItem3("15BEC05"+i));
        }
        return list;
    }
}
