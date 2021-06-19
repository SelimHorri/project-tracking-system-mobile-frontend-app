package com.selimhorri.pack.service.impl.sttc;

import android.content.Context;

import com.selimhorri.pack.exception.ObjectAlreadyExistsException;
import com.selimhorri.pack.exception.ObjectNotFoundException;
import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Credential;
import com.selimhorri.pack.service.CredentialService;

import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CredentialServiceStaticImpl implements CredentialService {

    private static final SortedMap<Integer, Credential> DUMMY_CREDENTIALS = new TreeMap<>();
    private final Context context;

    static {
        DUMMY_CREDENTIALS.put(1, new Credential(1, "imentouk", "$2y$04$8jC1Xb/fKB3EQIHy0XoFUunQNhjiVpvuMZys6iCOkphCAsyBkmCTC", true, "ROLE_EMP"));
        DUMMY_CREDENTIALS.put(2, new Credential(2, "badridoudi", "$2y$04$c09yvJ4rcadTRGaoVQRRZugld/9z377uaIHwRCWxexBADCVT.jC4S", true, "ROLE_EMP"));
        DUMMY_CREDENTIALS.put(3, new Credential(3, "selimhorri", "$2a$10$AQvL1xjHv3AMrzsX50.odeDSRixCv0GqVxHDGUOsFyWiIaWjsrN2u", true, "ROLE_EMP"));
        DUMMY_CREDENTIALS.put(4, new Credential(4, "admin", "$2y$04$HLi44N6cb6xmLYHdABF/euCgpk0LofYk4VdIeO1DAn.Ol1Bnaj3vW", true, "ROLE_ADMIN"));
        DUMMY_CREDENTIALS.put(5, new Credential(5, "soumayahajjem", "$2y$04$ljw6KJaAkzMzJZOf8eU6qOoq7jV2SXRqeg7uHS7tQb6x86SBS/oEW", true, "ROLE_MGR"));
        DUMMY_CREDENTIALS.put(6, new Credential(6, "nourlarguech", "$2y$04$ngbUBXKPaTRFAUFEifgPpuqmBTf4VjUJL.eGpeEIGwI/iiE18ZSny", true, "ROLE_MGR"));
        DUMMY_CREDENTIALS.put(7, new Credential(7, "johndoe", "$2y$04$CT3Jad4jrOq1zGt0Q4maEeTV57rdLtYNVnBM96vyVaGbaE4YgwfvO", true, "ROLE_MGR"));
    }

    public CredentialServiceStaticImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Credential>> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        final List<Credential> list = new ArrayList<>();
        for (Map.Entry<Integer, Credential> entry : DUMMY_CREDENTIALS.entrySet())
            list.add(entry.getValue());

    }

    @Override
    public void findById(final Integer credentialId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        if (!DUMMY_CREDENTIALS.containsKey(credentialId))
            throw new ObjectNotFoundException("#### Credential does not exist! ####");

    }

    @Override
    public void save(final Credential credential, final ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        if (DUMMY_CREDENTIALS.containsKey(credential.getCredentialId()))
            throw new ObjectAlreadyExistsException("#### Credential exists already ####");

        credential.setPassword(BCrypt.hashpw(credential.getPassword(), BCrypt.gensalt(4)));
        DUMMY_CREDENTIALS.put(credential.getCredentialId(), credential);
    }

    @Override
    public void update(final Credential credential, final ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        if (!DUMMY_CREDENTIALS.containsKey(credential.getCredentialId()))
            throw new ObjectNotFoundException("#### Credential does not exist ####");

        DUMMY_CREDENTIALS.get(credential.getCredentialId()).setUsername(credential.getUsername());
        DUMMY_CREDENTIALS.get(credential.getCredentialId()).setPassword(BCrypt.hashpw(credential.getPassword(), BCrypt.gensalt(4)));
        DUMMY_CREDENTIALS.get(credential.getCredentialId()).setEnabled(credential.getEnabled());
        DUMMY_CREDENTIALS.get(credential.getCredentialId()).setRole(credential.getRole());

    }

    @Override
    public void deleteById(final Integer credentialId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        if (!DUMMY_CREDENTIALS.containsKey(credentialId))
            throw new ObjectNotFoundException("#### Credential does not exist! ####");

        DUMMY_CREDENTIALS.remove(credentialId);
    }

    @Override
    public void findByUsername(String username, ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

    }


}
