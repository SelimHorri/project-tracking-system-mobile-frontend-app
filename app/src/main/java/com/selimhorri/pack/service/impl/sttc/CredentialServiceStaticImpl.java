package com.selimhorri.pack.service.impl.sttc;

import android.content.Context;

import com.selimhorri.pack.exception.ObjectAlreadyExistsException;
import com.selimhorri.pack.exception.ObjectNotFoundException;
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
    public DtoCollection<Credential> findAll() {

        final List<Credential> list = new ArrayList<>();
        for (Map.Entry<Integer, Credential> entry : DUMMY_CREDENTIALS.entrySet())
            list.add(entry.getValue());

        return new DtoCollection<>(list);
    }

    @Override
    public Credential findById(final Integer credentialId) {

        if (!DUMMY_CREDENTIALS.containsKey(credentialId))
            throw new ObjectNotFoundException("#### Credential does not exist! ####");

        return DUMMY_CREDENTIALS.get(credentialId);
    }

    @Override
    public Credential save(final Credential credential) {

        if (DUMMY_CREDENTIALS.containsKey(credential.getUserId()))
            throw new ObjectAlreadyExistsException("#### Credential exists already ####");

        DUMMY_CREDENTIALS.put(credential.getUserId(), credential);
        return DUMMY_CREDENTIALS.get(credential.getUserId());
    }

    @Override
    public Credential update(final Credential credential) {

        if (!DUMMY_CREDENTIALS.containsKey(credential.getUserId()))
            throw new ObjectNotFoundException("#### Credential does not exist ####");

        DUMMY_CREDENTIALS.get(credential.getUserId()).setUsername(credential.getUsername());
        DUMMY_CREDENTIALS.get(credential.getUserId()).setPassword(BCrypt.hashpw(credential.getPassword(), BCrypt.gensalt(4)));
        DUMMY_CREDENTIALS.get(credential.getUserId()).setEnabled(credential.getEnabled());
        DUMMY_CREDENTIALS.get(credential.getUserId()).setRole(credential.getRole());

        return DUMMY_CREDENTIALS.get(credential.getUserId());
    }

    @Override
    public void deleteById(final Integer credentialId) {

        if (!DUMMY_CREDENTIALS.containsKey(credentialId))
            throw new ObjectNotFoundException("#### Credential does not exist! ####");

        DUMMY_CREDENTIALS.remove(credentialId);
    }



}
