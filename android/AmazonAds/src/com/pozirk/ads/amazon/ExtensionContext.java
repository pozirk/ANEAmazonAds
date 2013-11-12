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
import java.util.HashMap;
import java.util.Map;

public class ExtensionContext extends FREContext
{
  public AmazonAdsManager _adMan = null;

  public void dispose()
  {
  }

  public Map<String, FREFunction> getFunctions()
  {
  	Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();

  	functionMap.put("init", new InitFunction());
  	functionMap.put("show", new ShowFunction());
  	functionMap.put("setTimeout", new SetTimeoutFunction());
  	functionMap.put("enableTesting", new EnableTestingFunction());
  	functionMap.put("enableLogging", new EnableLoggingFunction());
  	functionMap.put("hide", new HideFunction());
  	functionMap.put("dispose", new DisposeFunction());

  	return functionMap;
  }
}