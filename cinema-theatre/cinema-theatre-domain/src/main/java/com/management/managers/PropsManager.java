package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.management.dto.PropsDTO;
import com.management.entities.Props;
import com.management.interfaces.PropsManagerInterface;
import com.management.interfaces.UnitOfWorkInterface;

public class PropsManager implements PropsManagerInterface {

	private UnitOfWorkInterface uow;

	@Autowired
	public PropsManager(UnitOfWorkInterface uow) {
		this.uow = uow;
	}

	public boolean Create(PropsDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Props props;

		try {
			props = mapper.map(dto, Props.class);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		uow.getPropsRepository().Add(props);

		return true;
	}

	public PropsDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		PropsDTO dto;

		try {
			Props props = uow.getPropsRepository().Read(id);
			dto = mapper.map(props, PropsDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}

	public ArrayList<PropsDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Props> listEntities = uow.getPropsRepository().ReadAll();
		ArrayList<PropsDTO> listDTO = new ArrayList<PropsDTO>();

		for (Props tmp : listEntities) {
			try {
				PropsDTO dto = mapper.map(tmp, PropsDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(PropsDTO dto) {
		ModelMapper mapper = new ModelMapper();
		@SuppressWarnings("unused")
		Props tmp;

		try {
			tmp = mapper.map(dto, Props.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		uow.getHistoryRepository().Update();
		uow.commitChanges();

		return true;
	}

	public boolean Delete(int id) {
		try {
			uow.getPropsRepository().Delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}

		return true;
	}

}
