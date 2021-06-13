package com.selimhorri.pack.service;

import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Assignment;
import com.selimhorri.pack.model.dto.Credential;

public interface CredentialService {

    void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Credential>> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void findById(final Integer credentialId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void save(final Credential credential, final ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void update(final Credential credential, final ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void deleteById(final Integer credentialId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);

}
