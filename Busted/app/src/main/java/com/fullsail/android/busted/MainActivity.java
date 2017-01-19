// Ryan Backa
// Jav1-1609
// MainActivity

package com.fullsail.android.busted;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.fullsail.android.busted.net.GetDetailsTask;
import com.fullsail.android.busted.net.GetMembersTask;
import com.fullsail.android.busted.net.NetworkUtils;
import com.fullsail.android.busted.object.Member;

public class MainActivity extends Activity {

    private View mMembersListScreen;
    private View mMemberDetailsScreen;
    private ArrayList<Member> members;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);

        mMembersListScreen = findViewById(R.id.members_list_screen);
        mMemberDetailsScreen = findViewById(R.id.member_details_screen);
        if (NetworkUtils.isConnected(this)) {
            GetMembersTask task = new GetMembersTask(this);
            task.execute();
        }

    }

    public void showMembersListScreen(ArrayList<Member> _members) {
        mMemberDetailsScreen.setVisibility(View.GONE);
        mMembersListScreen.setVisibility(View.VISIBLE);

        ListView lv = (ListView) mMembersListScreen.findViewById(R.id.members_list);
        lv.setAdapter(new MembersAdapter(this, _members));
        members = _members;
        lv.setOnItemClickListener(mItemClickListener);
    }

    public void showMemberDetailsScreen(int _id) {

        if(NetworkUtils.isConnected(this)) {
            GetDetailsTask task = new GetDetailsTask(this);
            task.execute(_id);
        }
        mMembersListScreen.setVisibility(View.GONE);
        mMemberDetailsScreen.setVisibility(View.VISIBLE);
    }

    /**
     * Populate the member details screen with data.
     *
     * @param _name
     * @param _birthday
     * @param _gender
     * @param _twitterId
     * @param _numCommittees
     * @param _numRoles
     */
    public void populateMemberDetailsScreen(String _name, String _birthday, String _gender,
                                            String _twitterId, String _numCommittees, String _numRoles) {

        TextView tv = (TextView) findViewById(R.id.text_name);
        tv.setText(" "+_name.toString());

        tv = (TextView) findViewById(R.id.text_birthday);
        tv.setText(" "+_birthday.toString());

        tv = (TextView) findViewById(R.id.text_gender);
        tv.setText(" "+_gender.toString());

        tv = (TextView) findViewById(R.id.text_twitter_id);
        tv.setText(" "+_twitterId.toString());

        tv = (TextView) findViewById(R.id.text_num_committees);
        tv.setText(" "+_numCommittees.toString());

        tv = (TextView) findViewById(R.id.text_num_roles);
        tv.setText(" "+_numRoles.toString());
    }

    OnItemClickListener mItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> _parent, View _view, int _position, long _id) {
            // TODO: Show the members detail screen
            showMemberDetailsScreen(members.get(_position).getId());
        }

    };

    public void onBackPressed() {
        if (mMemberDetailsScreen.getVisibility() == View.VISIBLE) {
            mMemberDetailsScreen.setVisibility(View.GONE);
            mMembersListScreen.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }
}
