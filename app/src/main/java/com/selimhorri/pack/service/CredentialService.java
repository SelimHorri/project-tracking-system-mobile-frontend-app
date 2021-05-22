package com.selimhorri.pack.service;

import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Credential;

public interface CredentialService {

    DtoCollection<Credential> findAll();
    Credential findById(final Integer credentialId);
    Credential save(final Credential credential);
    Credential update(final Credential credential);
    void deleteById(final Integer credentialId);

}
