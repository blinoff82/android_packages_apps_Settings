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

package com.android.settings.aospextended.profiles.actions.item;

import com.android.internal.util.lineageos.profiles.AirplaneModeSettings;
import com.android.settings.aospextended.profiles.actions.ItemListAdapter;
import com.android.settings.R;

public class AirplaneModeItem extends BaseItem {
    AirplaneModeSettings mSettings;

    public AirplaneModeItem(AirplaneModeSettings airplaneModeSettings) {
        if (airplaneModeSettings == null) {
            airplaneModeSettings = new AirplaneModeSettings();
        }
        mSettings = airplaneModeSettings;
    }

    @Override
    public ItemListAdapter.RowType getRowType() {
        return ItemListAdapter.RowType.AIRPLANEMODE_ITEM;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getTitle() {
        return getString(R.string.sp_profile_airplanemode_title);
    }

    @Override
    public String getSummary() {
        return getString(getModeString(mSettings));
    }

    public AirplaneModeSettings getSettings() {
        return mSettings;
    }

    public static int getModeString(AirplaneModeSettings settings) {
        if (settings.isOverride()) {
            if (settings.getValue() == 1) {
                return R.string.sp_profile_action_enable;
            } else {
                return R.string.sp_profile_action_disable;
            }
        } else {
            return R.string.sp_profile_action_none;
        }
    }
}
