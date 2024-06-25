package com.petflicks.app.Service;

import com.petflicks.app.Models.Board;
import org.springframework.stereotype.Service;

import com.petflicks.app.Exception.InvalidLoginException;
import com.petflicks.app.Exception.UserNotFoundException;
import com.petflicks.app.Exception.UsernameAlreadyExists;
import com.petflicks.app.Models.User;

@Service
public interface BoardService {

	public Board create(Board newBoard);
	public int delete(Board board);
	public Board update(Board board);
	public Board findById(int boardId);
	public Board findByTitle(String title);

}
