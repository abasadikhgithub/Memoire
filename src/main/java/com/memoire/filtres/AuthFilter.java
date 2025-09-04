package com.memoire.filtres;

import java.io.IOException;

import com.memoire.model.AgentEtat;
import com.memoire.model.Medecin;
import com.memoire.model.Patient;
import com.memoire.model.Utilisateur;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*") // Intercepte toutes les requêtes de l'application
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String loginPage = req.getContextPath() + "/login.xhtml";
        String requestedUri = req.getRequestURI();
        
        boolean loggedIn = (session != null && session.getAttribute("utilisateurConnecte") != null);
        boolean isLoginPage = requestedUri.contains("/login.xhtml");
        boolean isInscriptionPage = requestedUri.contains("/inscriptionPatient.xhtml");
        boolean isPublicResource = requestedUri.contains("/public/") || requestedUri.contains("/javax.faces.resource/");

        // Permet l'accès aux pages de connexion, d'inscription et aux ressources publiques
        if (isLoginPage || isInscriptionPage || isPublicResource) {
            chain.doFilter(request, response);
            return;
        }

        // Si l'utilisateur est connecté, vérifie si son rôle correspond à l'URL demandée
        if (loggedIn) {
            Utilisateur user = (Utilisateur) session.getAttribute("utilisateurConnecte");
            String userRole = getUserRole(user);
            
            if (userRole != null && requestedUri.contains("/" + userRole + "/")) {
                chain.doFilter(request, response);
            } else {
                 // Redirige vers le tableau de bord si l'URL ne correspond pas au rôle
                String dashboardPath = getDashboardPath(user, req.getContextPath());
                res.sendRedirect(dashboardPath);
            }
        } else {
            // Redirige vers la page de connexion si l'utilisateur n'est pas connecté
            res.sendRedirect(loginPage);
        }
    }

    private String getUserRole(Utilisateur user) {
        if (user.isEstAdmin()) {
            return "admin";
        } else if (user instanceof Patient) {
            return "patient";
        } else if (user instanceof Medecin) {
            return "medecin";
        } else if (user instanceof AgentEtat) {
            return "agentetat";
        }
        return null;
    }
    
    private String getDashboardPath(Utilisateur user, String contextPath) {
        if (user.isEstAdmin()) {
            return contextPath + "/admin/dashboardAdmin.xhtml";
        } else if (user instanceof Patient) {
            return contextPath + "/patient/dashboardPatient.xhtml";
        } else if (user instanceof Medecin) {
            return contextPath + "/medecin/dashboardMedecin.xhtml";
        } else if (user instanceof AgentEtat) {
            return contextPath + "/agentetat/dashboardAgentEtat.xhtml";
        }
        return contextPath + "/public/index.xhtml";
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialisation du filtre
    }

    @Override
    public void destroy() {
        // Destruction du filtre
    }
}