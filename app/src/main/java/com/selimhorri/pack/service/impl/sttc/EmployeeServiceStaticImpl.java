package com.selimhorri.pack.service.impl.sttc;

import android.content.Context;

import com.selimhorri.pack.exception.ObjectAlreadyExistsException;
import com.selimhorri.pack.exception.ObjectNotFoundException;
import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Credential;
import com.selimhorri.pack.model.dto.Department;
import com.selimhorri.pack.model.dto.Employee;
import com.selimhorri.pack.model.dto.Location;
import com.selimhorri.pack.model.dto.Project;
import com.selimhorri.pack.model.dto.custom.EmployeeProjectData;
import com.selimhorri.pack.service.EmployeeService;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class EmployeeServiceStaticImpl implements EmployeeService {

    private static final SortedMap<Integer, Employee> DUMMY_EMPLOYEES = new TreeMap<>();
    private final Context context;

    static {
        DUMMY_EMPLOYEES.put(1, new Employee(1, "selim", "horri", "springabcxyzboot@gmail.com", "22125144", LocalDate.of(2019, Month.APRIL, 15), "billing", (double)5000, new Employee(4, "Soumaya", "Hajjem", "springabcxyzboot@gmail.com", "22125144", null, "billing", (double)5000, new Employee(), new Department(1, "billing", new Location(1, "RUE DE LA BOURSE", "2016", "LAC2")), new Credential(5, "soumayahajjem", "$2y$04$ljw6KJaAkzMzJZOf8eU6qOoq7jV2SXRqeg7uHS7tQb6x86SBS/oEW", true, "ROLE_MGR")), new Department(1, "billing", new Location(1, "RUE DE LA BOURSE", "2016", "LAC2")), new Credential(3, "selimhorri", "$2a$10$AQvL1xjHv3AMrzsX50.odeDSRixCv0GqVxHDGUOsFyWiIaWjsrN2u", true, "ROLE_EMP")));
        DUMMY_EMPLOYEES.put(2, new Employee(2, "badr", "Idoudi", "springabcxyzboot@gmail.com", "22125144", LocalDate.of(2019, Month.APRIL, 15), "Digital", (double)5000, new Employee(7, "admin", "admin", "springabcxyzboot@gmail.com", "22125144", null, "HR", (double)5000, new Employee(), new Department(), new Credential(7, "johndoe", "$2y$04$CT3Jad4jrOq1zGt0Q4maEeTV57rdLtYNVnBM96vyVaGbaE4YgwfvO", true, "ROLE_MGR")), new Department(3, "Digital", new Location(1, "RUE DE LA BOURSE", "2016", "LAC2")), new Credential(2, "badridoudi", "$2y$04$c09yvJ4rcadTRGaoVQRRZugld/9z377uaIHwRCWxexBADCVT.jC4S", true, "ROLE_EMP")));
        DUMMY_EMPLOYEES.put(3, new Employee(3, "Imen", "Touk", "springabcxyzboot@gmail.com", "22125144", LocalDate.of(2019, Month.APRIL, 15), "Data Warehouse", (double)5000, new Employee(5, "Nour", "Larguech", "springabcxyzboot@gmail.com", "22125144", null, "Chef service Data warehouse", (double)5000, new Employee(), new Department(2, "DWH", new Location(1, "RUE DE LA BOURSE", "2016", "LAC2")), new Credential(6, "nourlarguech", "$2y$04$ngbUBXKPaTRFAUFEifgPpuqmBTf4VjUJL.eGpeEIGwI/iiE18ZSny", true, "ROLE_MGR")), new Department(2, "DWH", new Location(1, "RUE DE LA BOURSE", "2016", "LAC2")), new Credential(1, "imentouk", "$2y$04$8jC1Xb/fKB3EQIHy0XoFUunQNhjiVpvuMZys6iCOkphCAsyBkmCTC", true, "ROLE_EMP")));
        DUMMY_EMPLOYEES.put(4, new Employee(4, "Soumaya", "Hajjem", "springabcxyzboot@gmail.com", "22125144", LocalDate.now(), "Chef service Billing", (double)5000, new Employee(), new Department(1, "billing", new Location(1, "RUE DE LA BOURSE", "2016", "LAC2")), new Credential(5, "soumayahajjem", "$2y$04$ljw6KJaAkzMzJZOf8eU6qOoq7jV2SXRqeg7uHS7tQb6x86SBS/oEW", true, "ROLE_MGR")));
        DUMMY_EMPLOYEES.put(5, new Employee(5, "Nour", "Larguech", "springabcxyzboot@gmail.com", "22125144", LocalDate.now(), "Chef service Data warehouse", (double)5000, new Employee(), new Department(2, "DWH", new Location(1, "RUE DE LA BOURSE", "2016", "LAC2")), new Credential(6, "nourlarguech", "$2y$04$ngbUBXKPaTRFAUFEifgPpuqmBTf4VjUJL.eGpeEIGwI/iiE18ZSny", true, "ROLE_MGR")));
        DUMMY_EMPLOYEES.put(6, new Employee(6, "John", "Doe", "springabcxyzboot@gmail.com", "22125144", LocalDate.now(), "Digital", (double)5000, new Employee(), new Department(3, "Digital", new Location(1, "RUE DE LA BOURSE", "2016", "LAC2")), new Credential(7, "johndoe", "$2y$04$CT3Jad4jrOq1zGt0Q4maEeTV57rdLtYNVnBM96vyVaGbaE4YgwfvO", true, "ROLE_MGR")));
        DUMMY_EMPLOYEES.put(7, new Employee(7, "admin", "admin", "springabcxyzboot@gmail.com", "22125144", LocalDate.now(), "HR", (double)5000, new Employee(), null, new Credential(7, "johndoe", "$2y$04$CT3Jad4jrOq1zGt0Q4maEeTV57rdLtYNVnBM96vyVaGbaE4YgwfvO", true, "ROLE_MGR")));
    }

    public EmployeeServiceStaticImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Employee>> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        final List<Employee> list = new ArrayList<>();
        for (Map.Entry<Integer, Employee> entry : DUMMY_EMPLOYEES.entrySet())
            list.add(entry.getValue());

    }

    @Override
    public void findById(final Integer employeeId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Employee> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        if (!DUMMY_EMPLOYEES.containsKey(employeeId))
            throw new ObjectNotFoundException("#### Employee does not exist! ####");

    }

    @Override
    public void save(final Employee employee, final ResponseCallbackListener.ResponseCallbackSuccessListener<Employee> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        if (DUMMY_EMPLOYEES.containsKey(employee.getEmployeeId()))
            throw new ObjectAlreadyExistsException("#### Employee exists already ####");

        DUMMY_EMPLOYEES.put(employee.getEmployeeId(), employee);
    }

    @Override
    public void update(final Employee employee, final ResponseCallbackListener.ResponseCallbackSuccessListener<Employee> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        if (!DUMMY_EMPLOYEES.containsKey(employee.getEmployeeId()))
            throw new ObjectNotFoundException("#### Employee does not exist ####");

        DUMMY_EMPLOYEES.get(employee.getEmployeeId()).setFirstName(employee.getFirstName());
        DUMMY_EMPLOYEES.get(employee.getEmployeeId()).setLastName(employee.getLastName());
        DUMMY_EMPLOYEES.get(employee.getEmployeeId()).setEmail(employee.getEmail());
        DUMMY_EMPLOYEES.get(employee.getEmployeeId()).setPhone(employee.getPhone());
        DUMMY_EMPLOYEES.get(employee.getEmployeeId()).setHiredate(employee.getHiredate());
        DUMMY_EMPLOYEES.get(employee.getEmployeeId()).setJob(employee.getJob());
        DUMMY_EMPLOYEES.get(employee.getEmployeeId()).setSalary(employee.getSalary());
        DUMMY_EMPLOYEES.get(employee.getEmployeeId()).setManager(employee.getManager());
        DUMMY_EMPLOYEES.get(employee.getEmployeeId()).setDepartment(employee.getDepartment());
        DUMMY_EMPLOYEES.get(employee.getEmployeeId()).setUserCredential(employee.getUserCredential());

    }

    @Override
    public void deleteById(final Integer employeeId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        if (!DUMMY_EMPLOYEES.containsKey(employeeId))
            throw new ObjectNotFoundException("#### Employee does not exist! ####");

        DUMMY_EMPLOYEES.remove(employeeId);
    }

    @Override
    public void findByUsername(String username, ResponseCallbackListener.ResponseCallbackSuccessListener<Employee> response, ResponseCallbackListener.ResponseCallbackErrorListener error) {

    }

    @Override
    public void findByEmployeeId(Integer employeeId, ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<EmployeeProjectData>> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

    }


}
