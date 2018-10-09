package telran.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.forum.api.ForumURI;
import telran.forum.domain.Post;
import telran.forum.dto.DatePeriodDto;
import telran.forum.dto.NewCommentDto;
import telran.forum.dto.NewPostDto;
import telran.forum.dto.PostUpdateDto;
import telran.forum.service.ForumService;

@RestController
public class ForumController {

	@Autowired
	ForumService forumService;

	@PostMapping(ForumURI.POST)
	public Post addNewPost(@RequestBody NewPostDto newPost) {
		
		return forumService.addNewPost(newPost);

	}

	@GetMapping(ForumURI.POST + "/{id}")
	public Post getPost(@PathVariable String id) {

		return forumService.getPost(id);

	}

	@DeleteMapping(ForumURI.POST + "/{id}")
	public Post removePost(@PathVariable String id) {
		return forumService.removePost(id);

	}

	@PutMapping(ForumURI.POST)
	public Post updatePost(@RequestBody PostUpdateDto updatePost) {
		return forumService.updatePost(updatePost);
	}

	@PutMapping(ForumURI.LIKE + "/{id}")
	public boolean addLike(@PathVariable String id) {

		return forumService.addLike(id);
	}

	@PutMapping(ForumURI.COMMENT + "/{id}")
	public Post addComment(@PathVariable String id, @RequestBody NewCommentDto newComment) {
		return forumService.addComment(id, newComment);
	}
	
	@PutMapping(ForumURI.COMMENT + ForumURI.LIKE + "/{id}")
	public boolean addCommentLike(@PathVariable String id, @RequestBody NewCommentDto newComment) {
		
		return forumService.addCommentLike(id, newComment);
		
	}

	@PostMapping(ForumURI.POSTS + ForumURI.TAGS)
	public Iterable<Post> findByTags(@RequestBody List<String> tags) {
		return forumService.findByTags(tags);
	}

	@GetMapping(ForumURI.POSTS + "/{author}")
	public Iterable<Post> findByAuthor(@PathVariable String author) {
		return forumService.findByAuthor(author);
	}

	@PostMapping(ForumURI.POSTS + ForumURI.DATES)
	public Iterable<Post> findByDate(@RequestBody DatePeriodDto period) {
		return forumService.findByDate(period);
	}
	
}
