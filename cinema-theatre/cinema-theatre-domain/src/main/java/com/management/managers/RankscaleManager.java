package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.RankscaleDTO;
import com.management.entities.Rankscale;
import com.management.interfaces.RankscaleManagerInterface;
import com.management.repositories.RankscaleRepository;

/**
 * @author Nikola Stojanovic
 *
 */
@Service
@Transactional
public class RankscaleManager implements RankscaleManagerInterface {

	private RankscaleRepository rankscaleRepository;
	
	@Autowired
	public RankscaleManager(RankscaleRepository rankscaleRepository) {
		this.rankscaleRepository = rankscaleRepository;
	}

	/* (non-Javadoc)
	 * @see com.management.interfaces.ManagerInterface#Create(java.lang.Object)
	 */
	public boolean Create(RankscaleDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Rankscale rs;

		try {
			rs = mapper.map(dto, Rankscale.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		rankscaleRepository.save(rs);

		return true;
	}

	/* (non-Javadoc)
	 * @see com.management.interfaces.ManagerInterface#Read(int)
	 */
	public RankscaleDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		RankscaleDTO dto;

		try {
			Rankscale rs = rankscaleRepository.findOne(id);
			dto = mapper.map(rs, RankscaleDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}

	/* (non-Javadoc)
	 * @see com.management.interfaces.ManagerInterface#ReadAll()
	 */
	public ArrayList<RankscaleDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Rankscale> listEntities = (ArrayList<Rankscale>) rankscaleRepository.findAll();
		ArrayList<RankscaleDTO> listDTO = new ArrayList<RankscaleDTO>();

		for (Rankscale tmp : listEntities) {
			try {
				RankscaleDTO dto = mapper.map(tmp, RankscaleDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	/* (non-Javadoc)
	 * @see com.management.interfaces.ManagerInterface#Update(java.lang.Object)
	 */
	public boolean Update(RankscaleDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Rankscale tmp;

		try {
			tmp = mapper.map(dto, Rankscale.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		rankscaleRepository.save(tmp);
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.management.interfaces.ManagerInterface#Delete(int)
	 */
	public boolean Delete(int id) {
		try {
			rankscaleRepository.delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		
		return true;
	}

}
