package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.ModelException;
import model.Post;
import model.User;
import model.dao.DAOFactory;
import model.dao.PostDAO;


@WebServlet(urlPatterns = {"/posts","/post/save","/post/update","/post/delete"})

public class PostsController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		String action = req.getRequestURI();
		System.out.println("GET: " + action);

		switch (action) {
			case "/facebook/posts": {
				loadPosts(req);
				RequestDispatcher rd = req.getRequestDispatcher("posts.jsp");
				rd.forward(req, resp);
				break;
			}
			
			case "/facebook/post/update": {
			    try {
					loadPost(req);
				} catch (ModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // carrega o post pra edição
			    RequestDispatcher rd = req.getRequestDispatcher("/form_post.jsp");
			    rd.forward(req, resp);
			    break;
			}
			
			case "/facebook/post/delete": {
			    String postIdStr = req.getParameter("id");

			    if (postIdStr != null && !postIdStr.isEmpty()) {
			        try {
			            int postId = Integer.parseInt(postIdStr);

			            Post post = new Post();
			            post.setId(postId); // cria objeto só com ID

			            PostDAO dao = DAOFactory.createDAO(PostDAO.class);
			            dao.delete(post); // passa o objeto post

			        } catch (NumberFormatException | ModelException e) {
			            e.printStackTrace();
			            req.setAttribute("error", "Erro ao deletar post.");
			        }
			    }

			    resp.sendRedirect(req.getContextPath() + "/posts");
			    break;
			}

			
			default:
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {

		String action = req.getRequestURI();
		System.out.println("POST: " + action);

		switch (action) {
		case "/facebook/post/save": {
		    String postId = req.getParameter("post_id");
		    
		    PostDAO dao = DAOFactory.createDAO(PostDAO.class);

		    try {
		        if (postId != null && !postId.equals("")) {
		            Post post = createPost(req);
		            post.setId(Integer.parseInt(postId));
		            dao.update(post);
		        } else {
		            Post newPost = createPost(req);
		            dao.save(newPost);
		        }
		    } catch (ModelException e) {
		        e.printStackTrace();
		    }

		    resp.sendRedirect(req.getContextPath() + "/posts");
		    break;
		}

			default:
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private void loadPosts(HttpServletRequest req) {
		PostDAO dao = DAOFactory.createDAO(PostDAO.class);

		try {
			List<Post> posts = dao.listAll();
			req.setAttribute("posts", posts);
		} catch (ModelException e) {
			e.printStackTrace();
		}
	}

	private Post createPost(HttpServletRequest req) {
	    Post post = new Post();
	    
	    String content = req.getParameter("content");

	    // Pegar o usuário direto da sessão
	    User user = (User) req.getSession().getAttribute("usuario_logado");

	    post.setContent(content);
	    post.setUser(user); // já é o usuário autenticado

	    return post;
	}
	
	private void loadPost(HttpServletRequest req) throws ModelException {
	    String postIdStr = req.getParameter("id");  // Verifica o parâmetro "id" na URL
	    
	    if (postIdStr != null && !postIdStr.isEmpty()) {
	        try {
	            int postId = Integer.parseInt(postIdStr);

	            PostDAO dao = DAOFactory.createDAO(PostDAO.class);
	            Post post = dao.findById(postId);

	            if (post == null) {
	                req.setAttribute("error", "Post não encontrado.");
	            } else {
	                req.setAttribute("post", post);
	            }
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            req.setAttribute("error", "ID do post inválido.");
	        }
	    } else {
	        req.setAttribute("error", "ID do post não informado.");
	    }
	}




	
}
