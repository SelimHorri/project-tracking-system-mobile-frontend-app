package com.selimhorri.pack.service.impl.sttc;

import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.dto.custom.AuthenticationRequest;
import com.selimhorri.pack.model.dto.custom.AuthenticationResponse;
import com.selimhorri.pack.service.AuthenticationService;

public class AuthenticationServiceStaticImpl implements AuthenticationService {

    @Override
    public void authenticate(AuthenticationRequest authenticationRequest, ResponseCallbackListener.ResponseCallbackSuccessListener<AuthenticationResponse> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

    }



}
