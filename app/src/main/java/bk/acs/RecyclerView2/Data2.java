package bk.acs.RecyclerView2;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bk on 07-03-2017.
 */

public class Data2 {
    Context context;
    public List<ListItem2> list=new ArrayList<>();
    public Data2(Context ctx)
    {
        context=ctx;
    }
    public List getList()
    {
        for(int i=0;i<20;i++)
        {
            list.add(new ListItem2(("15BEC05"+i),0));
        }
        return list;
    }
}
