package org.kitri.services.sales.employee.dto;

public class SvcComEmpPosDto {
	private String positionId;
	private String positionName;

	public SvcComEmpPosDto() {
		super();
	}

	public SvcComEmpPosDto(String positionId, String positionName) {
		super();
		this.positionId = positionId;
		this.positionName = positionName;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	
}
