/*
 * Copyright (c) 2018 Nike, inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.halyard.cli.command.v1.config.canary.signalfx.account;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.netflix.spinnaker.halyard.cli.command.v1.config.canary.account.AbstractEditCanaryAccountCommand;
import com.netflix.spinnaker.halyard.config.model.v1.canary.AbstractCanaryAccount;
import com.netflix.spinnaker.halyard.config.model.v1.canary.signalfx.SignalfxCanaryAccount;

@Parameters(separators = "=")
public class SignalfxEditCanaryAccountCommand extends AbstractEditCanaryAccountCommand<SignalfxCanaryAccount> {

  @Override
  protected String getServiceIntegration() {
    return "Signalfx";
  }

  @Parameter(
      names = "--base-url",
      description = "The base URL to the SignalFx server."
  )
  private String baseUrl;

  @Parameter(
      names = "--access-token",
      password = true,
      description = "The SignalFx access token."
  )
  private String accessToken;

  @Override
  protected AbstractCanaryAccount editAccount(SignalfxCanaryAccount account) {
    account.setEndpoint(isSet(baseUrl) ? new SignalfxCanaryAccount.Endpoint().setBaseUrl(baseUrl) : account.getEndpoint());
    account.setAccessToken(isSet(accessToken) ? accessToken : account.getAccessToken());

    return account;
  }
}
