package servlets;

import com.mongodb.MongoClient;
import dao.srtMongoDBPersonDAO;
import entity.Skills;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addCompetence")
public class AddCompetenceServlet extends HttpServlet {

    private static final long serialVersionUID = 2421756993723050062L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String skill = request.getParameter("skill");
        String text1 = request.getParameter("text1");
        if ((skill == null || skill.equals(""))
                || (text1 == null || text1.equals(""))) {
            request.setAttribute("error", "Mandatory Parameters Missing");
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/persons.jsp");
            rd.forward(request, response);
        } else {
            Skills p = new Skills();
            p.setSkill(skill);
            p.setText1(text1);
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            srtMongoDBPersonDAO personDAO = new srtMongoDBPersonDAO(mongo);
           // personDAO.createPerson(p);
            System.out.println("Person Added Successfully with id="+p.getId());
            request.setAttribute("success", "Person Added Successfully");
            //List<Skills> skills = personDAO.readAllPerson();
            request.setAttribute("skill", skill);

            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/persons.jsp");
            rd.forward(request, response);
        }
    }


}
