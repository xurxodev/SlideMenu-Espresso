import android.test.ActivityInstrumentationTestCase2;

import com.google.android.apps.common.testing.ui.espresso.action.ViewActions;
import com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers;

import info.androidhive.slidingmenu.MainActivity;
import info.androidhive.slidingmenu.R;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onData;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.contrib.DrawerActions.closeDrawer;
import static com.google.android.apps.common.testing.ui.espresso.contrib.DrawerActions.openDrawer;
import static com.google.android.apps.common.testing.ui.espresso.contrib.DrawerMatchers.isClosed;
import static com.google.android.apps.common.testing.ui.espresso.contrib.DrawerMatchers.isOpen;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

/**
 * Created by xurxo on 25/11/2014.
 */
public class MainTest extends
        ActivityInstrumentationTestCase2<MainActivity> {

    public MainTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        getActivity();
    }

    @Override
    public void tearDown() throws Exception {

    }

    public void testOpenCloseDrawer() {
        // Drawer should not be open to start.
        onView(withId(R.id.drawer_layout)).check(matches(isClosed()));

        openDrawer(R.id.drawer_layout);

        // The drawer should now be open.
        onView(withId(R.id.drawer_layout)).check(matches(isOpen()));

        closeDrawer(R.id.drawer_layout);

        // Drawer should be closed again.
        onView(withId(R.id.drawer_layout)).check(matches(isClosed()));
    }

    public void testVerifyFindPeopleOpenedFromDrawer() {
        openDrawer(R.id.drawer_layout);

        onData(anything())
                .inAdapterView(withId(R.id.list_slidermenu))
                .atPosition(1)
                .perform(ViewActions.click());

        onView(allOf(withText("Find People"),withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))).check(matches(isDisplayed()));
    }
}
