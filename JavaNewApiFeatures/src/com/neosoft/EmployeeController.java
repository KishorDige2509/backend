package com.neosoft;

@Slf4j
@RestContoller
public class EmployeeController {
	
	private static final CLASSNAME= EmployeeController.class.getSimpleName();
	
	@Autowired
	private EmployeeService service;
	
	@PostMapping("save/Employee")
	public Map<String, Object> saveEmployee(@RequestBody EmployeeDTO dto, @RequestHeader MultiValueMap<String, String> headers) {
		log.info(LogUtil.presentLog(CLASSNAME));
		return service.saveEmployee(dto, headers);		
	}

	@PostMapping("get/Employee/{employeeId}")
	public Map<String, Object> getEmployee(@RequestParam("employeeId") Long id, @RequestHeader MultiValueMap<String, String> headers) {
		log.info(LogUtil.presentLog(CLASSNAME));
		return service.getEmployee(id, headers);		
	}
}
