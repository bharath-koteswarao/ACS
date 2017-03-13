package bk.acs;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawer extends Fragment {
    LinearLayout note, help, settings, rate, home;
    ActionBarDrawerToggle drawertoggle;
    DrawerLayout drawerlayout;

    public NavigationDrawer() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        note = (LinearLayout) v.findViewById(R.id.note);
        help = (LinearLayout) v.findViewById(R.id.help);
        settings = (LinearLayout) v.findViewById(R.id.settings);
        rate = (LinearLayout) v.findViewById(R.id.rate);
        return v;
    }

    public void setUp(DrawerLayout drawerlayout, Toolbar toolbar) {
        this.drawerlayout = drawerlayout;
        drawertoggle = new ActionBarDrawerToggle(getActivity(), drawerlayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        drawerlayout.setDrawerListener(drawertoggle);
        drawerlayout.post(new Runnable() {
            @Override
            public void run() {
                drawertoggle.syncState();
            }
        });
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Note.class));
            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Rate.class));
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Settings.class));
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Help.class));
            }
        });
    }
}
