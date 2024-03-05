package tn.esprit.controllers;

import tn.esprit.modeles.Commentaire;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsService {
    public Map<String, Integer> calculateCommentsPerDay(List<Commentaire> comments) {
        Map<String, Integer> commentsPerDay = new HashMap<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Commentaire comment : comments) {
            String day = dateFormat.format(comment.getDate());

            commentsPerDay.put(day, commentsPerDay.getOrDefault(day, 0) + 1);
        }

        return commentsPerDay;
    }
}
