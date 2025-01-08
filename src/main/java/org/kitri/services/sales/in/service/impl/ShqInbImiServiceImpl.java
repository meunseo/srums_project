package org.kitri.services.sales.in.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.kitri.services.sales.in.dao.ShqInbImiDao;
import org.kitri.services.sales.in.entity.IntegrationInbound;
import org.kitri.services.sales.in.service.ShqInbImiService;
import org.kitri.services.sales.repo.dto.ShqInbImiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("shqInbImiService")
public class ShqInbImiServiceImpl implements ShqInbImiService{
	private final ShqInbImiDao dao;
	
	@Autowired
	public ShqInbImiServiceImpl(@Qualifier("shqInbImiDao")
	 						 ShqInbImiDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<ShqInbImiDto> getIncomingInbound(){
		List<IntegrationInbound> entity = dao.findByStatus("N");
		List<ShqInbImiDto> dto = new ArrayList<>();
		
		for (IntegrationInbound i : entity) {
			System.out.println(i.getUpdateDate());
			dto.add(new ShqInbImiDto()
					.setInboundDate(i.getInboundDate())
					.setGoodsId(i.getGoodsId())
					.setInboundQty(i.getInboundQty())
					.setConfirmStatus(i.getConfirmStatus())
					.setUpdateDate(i.getUpdateDate())
					.setGoodsName(i.getGoodsName())
					);
		}
		return dto; 
	}
	
	@Override
	public List<ShqInbImiDto> getApproveInbound(){
		List<IntegrationInbound> entity = dao.findByStatus("Y");
		List<ShqInbImiDto> dto = new ArrayList<>();
		
		for (IntegrationInbound i : entity) {
			System.out.println(i.getUpdateDate());
			dto.add(new ShqInbImiDto()
					.setInboundDate(i.getInboundDate())
					.setGoodsId(i.getGoodsId())
					.setInboundQty(i.getInboundQty())
					.setConfirmStatus(i.getConfirmStatus())
					.setUpdateDate(i.getUpdateDate())
					.setGoodsName(i.getGoodsName())
					);
		}
		return dto; 
	}

	@Override
	public boolean updateIncomingStocks(ShqInbImiDto updateDto) {
		
		int updateCol = 0;
		IntegrationInbound entity = new IntegrationInbound();
		entity.setInboundDate(Timestamp.valueOf(updateDto.getInboundDate()))
			  .setGoodsId(updateDto.getGoodsId())
			  .setInboundQty(updateDto.getInboundQty())
			  .setConfirmStatus(updateDto.getConfirmStatus());
		updateCol += dao.update(entity);
		if(updateCol > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void approveStock(ShqInbImiDto updateDto) {
		IntegrationInbound entity = new IntegrationInbound();
		entity.setInboundDate(Timestamp.valueOf(updateDto.getInboundDate()))
			  .setGoodsId(updateDto.getGoodsId())
			  .setInboundQty(updateDto.getInboundQty())
			  .setConfirmStatus(updateDto.getConfirmStatus());
		dao.approve(entity);
	}
	
	@Override
	public List<ShqInbImiDto> getInbounds() {
		List<IntegrationInbound> entity = dao.findAll();
		List<ShqInbImiDto> dto = new ArrayList<>();
		
		for (IntegrationInbound i : entity) {
			System.out.println(i.getUpdateDate());
			dto.add(new ShqInbImiDto()
					.setInboundDate(i.getInboundDate())
					.setGoodsId(i.getGoodsId())
					.setInboundQty(i.getInboundQty())
					.setConfirmStatus(i.getConfirmStatus())
					.setUpdateDate(i.getUpdateDate())
					.setGoodsName(i.getGoodsName())
					);
		}
		return dto; 
	}
	
}