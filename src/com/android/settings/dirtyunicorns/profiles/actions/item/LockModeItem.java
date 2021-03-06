/*
 * Copyright (C) 2012 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.dirtyunicorns.profiles.actions.item;

import com.android.internal.util.lineageos.app.Profile;
import com.android.settings.R;
import com.android.settings.dirtyunicorns.profiles.actions.ItemListAdapter;

public class LockModeItem extends BaseItem {
    Profile mProfile;

    public LockModeItem(Profile profile) {
        mProfile = profile;
    }

    @Override
    public ItemListAdapter.RowType getRowType() {
        return ItemListAdapter.RowType.LOCKSCREENMODE_ITEM;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getTitle() {
        return getString(R.string.sp_profile_lockmode_title);
    }

    @Override
    public String getSummary() {
        return getString(getSummaryString(mProfile));
    }

    public static int getSummaryString(Profile profile) {
        switch (profile.getScreenLockMode().getValue()) {
            case Profile.LockMode.DEFAULT:
                return R.string.sp_profile_action_system; //"leave unchanged"
            case Profile.LockMode.DISABLE:
                return R.string.sp_profile_lockmode_disabled_summary;
            case Profile.LockMode.INSECURE:
                return R.string.sp_profile_lockmode_insecure_summary;
            default: return 0;
        }
    }
}