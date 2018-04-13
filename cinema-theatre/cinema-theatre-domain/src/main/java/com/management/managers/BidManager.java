/**
 * 
 */
package com.management.managers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.BidDTO;
import com.management.entities.Bid;
import com.management.entities.User;
import com.management.interfaces.BidManagerInterface;
import com.management.repositories.BidRepository;

/**
 * @author Nikola Stojanovic
 *
 */
@Service
@Transactional
public class BidManager implements BidManagerInterface {

	private BidRepository bidRepository;

	@Autowired
	public BidManager(BidRepository bidRepository) {
		this.bidRepository = bidRepository;
	}

	public boolean Create(BidDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Bid bid;

		try {
			bid = mapper.map(dto, Bid.class);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		bidRepository.save(bid);

		return true;
	}

	public BidDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		BidDTO dto;

		try {
			Bid bid = bidRepository.findOne(id);
			dto = mapper.map(bid, BidDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}

	public ArrayList<BidDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Bid> listEntities = (ArrayList<Bid>) bidRepository.findAll();
		ArrayList<BidDTO> listDTO = new ArrayList<BidDTO>();

		for (Bid tmp : listEntities) {
			try {
				BidDTO dto = mapper.map(tmp, BidDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(BidDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Bid tmp;

		try {
			tmp = mapper.map(dto, Bid.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		bidRepository.save(tmp);

		return true;
	}

	public boolean Delete(int id) {
		try {
			bidRepository.delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}

		return true;
	}

	public List<BidDTO> getBids(User user) {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Bid> listEntities = (ArrayList<Bid>) bidRepository.findByUser(user);
		ArrayList<BidDTO> listDTO = new ArrayList<BidDTO>();

		for (Bid tmp : listEntities) {
			try {
				BidDTO dto = mapper.map(tmp, BidDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

}
