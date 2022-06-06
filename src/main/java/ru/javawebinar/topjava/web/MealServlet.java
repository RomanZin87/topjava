package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealRepoInMemoryImpl;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private static final int CALORIES_PER_DAY = 2000;

    private MealRepository mealRepository;

    @Override
    public void init() {
        mealRepository = new MealRepoInMemoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to meals");
        String action = request.getParameter("action");

        if (action == null) {
            log.debug("getAll meals");
            request.setAttribute("mealTos", makeTos());
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            log.debug("Delete meal with id={} ", id);
            mealRepository.delete(id);
            response.sendRedirect("meals");
        } else if (action.equals("create")){
            Meal meal = new Meal(LocalDateTime.now(), "description", 0);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealForm.jsp").forward(request, response);
        } else if (action.equals("update")) {
            Meal meal = mealRepository.get(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealForm.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("forward to mealForm");
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        meal.setId(id.isEmpty() ? null : Integer.valueOf(id));
        log.debug(meal.hasNoId() ? "Create new meal " : "Update meal with id={} ", id);
        mealRepository.save(meal);
        response.sendRedirect("meals");
    }

    private List<MealTo> makeTos() {
        return MealsUtil.filteredByStreams(mealRepository.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
    }
}
