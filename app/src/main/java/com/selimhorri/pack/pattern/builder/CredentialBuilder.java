package com.selimhorri.pack.pattern.builder;

import com.selimhorri.pack.constant.RoleEnum;
import com.selimhorri.pack.model.dto.Credential;

public class CredentialBuilder {

    private final Credential credential;

    public CredentialBuilder() {
        this.credential = new Credential();
    }

    public CredentialBuilder(final Credential credential) {
        this.credential = credential;
    }

    public Credential build() {
        return this.credential;
    }

    public CredentialBuilder withCredentialId(final int credentialId) {
        this.credential.setCredentialId(credentialId);
        return this;
    }

    public CredentialBuilder withUsername(final String username) {
        this.credential.setUsername(username);
        return this;
    }

    public CredentialBuilder withPassword(final String password) {
        this.credential.setPassword(password);
        return this;
    }

    public CredentialBuilder withEnabled(final boolean enabled) {
        this.credential.setEnabled(enabled);
        return this;
    }

    public CredentialBuilder withRole(final RoleEnum role) {
        this.credential.setRole(role.toString());
        return this;
    }



}
