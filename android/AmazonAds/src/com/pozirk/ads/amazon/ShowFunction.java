/* Copyright (c) 2013 Pozirk Games
 * http://www.pozirk.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pozirk.ads.amazon;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class ShowFunction implements FREFunction
{
  public FREObject call(FREContext frectx, FREObject[] args)
  {
  	ExtensionContext ctx = (ExtensionContext)frectx;

  	try
    {
    	FREObject size = args[0];
    	FREObject halign = args[1];
    	FREObject valign = args[2];
    	
    	ctx._adMan.show(size.getAsInt(), halign.getAsInt(), valign.getAsInt());
    }
  	catch(Exception e)
    {
    	e.printStackTrace();
    	ctx.dispatchStatusEventAsync("AD_SHOW_FAIL", "type: banner, "+e.getMessage());
    }

  	return null;
  }
}