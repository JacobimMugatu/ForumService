package telran.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
<<<<<<< HEAD
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
=======
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.forum.domain.Post;
import telran.forum.dto.DatePeriodDto;
import telran.forum.dto.NewCommentDto;
import telran.forum.dto.NewPostDto;
import telran.forum.dto.PostUpdateDto;
import telran.forum.service.ForumService;

@RestController
@RequestMapping("/forum")
public class ForumController {
	
	@Autowired
	ForumService service;
	
	@PostMapping("/post")
	public Post addPost(@RequestBody NewPostDto newPost) {
		return service.addNewPost(newPost);
	}
	
	@GetMapping("/post/{id}")
	public Post getPost(@PathVariable String id) {
		return service.getPost(id);
	}
	
	@DeleteMapping("/post/{id}")
	public Post removePost(@PathVariable String id) {
		return service.removePost(id);
	}
	
	@PutMapping("/post")
	public Post updatePost(@RequestBody PostUpdateDto postUpdateDto) {
		return service.updatePost(postUpdateDto);
	}
	
	@PutMapping("/post/{id}/like")
	public boolean addLike(@PathVariable String id) {
		return service.addLike(id);
	}
	
	@PutMapping("/post/{id}/comment")
	public Post addComment(@PathVariable String id, @RequestBody NewCommentDto newCommentDto) {
		return service.addComment(id, newCommentDto);
	}
	
	@PostMapping("/posts/tags")
	public Iterable<Post> getPostsByTags(@RequestBody List<String> tags){
		return service.findByTags(tags);
	}
	
	@GetMapping("/posts/author/{author}")
	public Iterable<Post> getPostsByAuthor(String author){
		return service.findByAuthor(author);
	}
	
	@PostMapping("/posts/period")
	public Iterable<Post> getPostsBetweenDate(@RequestBody DatePeriodDto periodDto){
		return service.findByDate(periodDto);
	}

}





>>>>>>> branch 'master' of https://github.com/TelRanJava23/ForumService.git
