package appsforyou.junkcleaner.PagerAdapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import appsforyou.junkcleaner.Fragments.PhoneBooster;


/**
 * Created by intag pc on 2/12/2017.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public MyPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                PhoneBooster tab1 = new PhoneBooster();
                return tab1;
            case 1:
                PhoneBooster tab2 = new PhoneBooster();

                return tab2;
            case 2:
                PhoneBooster tab3 = new PhoneBooster();

                return tab3;
            case 3:
                PhoneBooster tab4 = new PhoneBooster();

                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
