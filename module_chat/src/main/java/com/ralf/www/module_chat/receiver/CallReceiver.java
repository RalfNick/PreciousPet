/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ralf.www.module_chat.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hyphenate.util.EMLog;
import com.ralf.www.module_chat.ChatHelper;
import com.ralf.www.module_chat.view.VideoCallActivity;
import com.ralf.www.module_chat.view.VoiceCallActivity;

public class CallReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		if(!ChatHelper.getInstance().isLoggedIn())
		    return;
		//userId
		String from = intent.getStringExtra("from");
		//call type
		String type = intent.getStringExtra("type");
		if("video".equals(type)){ //video call
		    context.startActivity(new Intent(context, VideoCallActivity.class).
                    putExtra("userId", from).putExtra("isComingCall", true).
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
		}else{ //voice call
		    context.startActivity(new Intent(context, VoiceCallActivity.class).
		            putExtra("userId", from).putExtra("isComingCall", true).
		            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
		}
		EMLog.d("CallReceiver", "app received a incoming call");
	}

}
