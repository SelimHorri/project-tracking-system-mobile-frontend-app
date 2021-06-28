package com.selimhorri.pack.service;

import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Credential;

public interface CredentialService {

    void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Credential>> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);
    void findById(final Integer credentialId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);
    void save(final Credential credential, final ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);
    void update(final Credential credential, final ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);
    void deleteById(final Integer credentialId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);
    void findByUsername(final String username, final ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);

}
