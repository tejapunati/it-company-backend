package com.ssrmtech.itcompany.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/services")
    public ResponseEntity<List<Map<String, Object>>> getServices() {
        List<Map<String, Object>> services = new ArrayList<>();
        
        Map<String, Object> service1 = new HashMap<>();
        service1.put("title", "IT Staffing");
        service1.put("icon", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" width=\"48\" height=\"48\"><path fill=\"currentColor\" d=\"M20 6h-4V4c0-1.1-.9-2-2-2h-4c-1.1 0-2 .9-2 2v2H4c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V8c0-1.1-.9-2-2-2zM10 4h4v2h-4V4zm10 16H4V8h16v12z\"/><path fill=\"currentColor\" d=\"M13 10h-2v3H8v2h3v3h2v-3h3v-2h-3z\"/></svg>");
        service1.put("description", "Connect with top tech talent across all technology domains to power your digital innovation.");
        services.add(service1);
        
        Map<String, Object> service2 = new HashMap<>();
        service2.put("title", "Software Development");
        service2.put("icon", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" width=\"48\" height=\"48\"><path fill=\"currentColor\" d=\"M9.4 16.6L4.8 12l4.6-4.6L8 6l-6 6 6 6 1.4-1.4zm5.2 0l4.6-4.6-4.6-4.6L16 6l6 6-6 6-1.4-1.4z\"/></svg>");
        service2.put("description", "Custom software solutions designed to meet your specific business requirements and challenges.");
        services.add(service2);
        
        Map<String, Object> service3 = new HashMap<>();
        service3.put("title", "Cloud Services");
        service3.put("icon", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" width=\"48\" height=\"48\"><path fill=\"currentColor\" d=\"M19.35 10.04C18.67 6.59 15.64 4 12 4 9.11 4 6.6 5.64 5.35 8.04 2.34 8.36 0 10.91 0 14c0 3.31 2.69 6 6 6h13c2.76 0 5-2.24 5-5 0-2.64-2.05-4.78-4.65-4.96z\"/></svg>");
        service3.put("description", "Comprehensive cloud solutions for scalable, secure, and resilient infrastructure.");
        services.add(service3);
        
        Map<String, Object> service4 = new HashMap<>();
        service4.put("title", "IT Consulting");
        service4.put("icon", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" width=\"48\" height=\"48\"><path fill=\"currentColor\" d=\"M19 3H5c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm0 16H5V5h14v14z\"/><path fill=\"currentColor\" d=\"M7 10h2v7H7zm4-3h2v10h-2zm4 6h2v4h-2z\"/></svg>");
        service4.put("description", "Strategic technology guidance to optimize your IT infrastructure and drive business growth.");
        services.add(service4);
        
        return ResponseEntity.ok(services);
    }
    
    @GetMapping("/technologies")
    public ResponseEntity<List<Map<String, Object>>> getTechnologies() {
        List<Map<String, Object>> technologies = new ArrayList<>();
        
        Map<String, Object> tech1 = new HashMap<>();
        tech1.put("name", "Web Development");
        tech1.put("icon", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" width=\"48\" height=\"48\"><path fill=\"currentColor\" d=\"M20 4H4c-1.1 0-1.99.9-1.99 2L2 18c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zm-5 14H4v-4h11v4zm0-5H4V9h11v4zm5 5h-4V9h4v9z\"/></svg>");
        tech1.put("tags", List.of("React", "Angular", "Node.js", "Vue", "Next.js"));
        technologies.add(tech1);
        
        Map<String, Object> tech2 = new HashMap<>();
        tech2.put("name", "Cloud Computing");
        tech2.put("icon", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" width=\"48\" height=\"48\"><path fill=\"currentColor\" d=\"M19.35 10.04C18.67 6.59 15.64 4 12 4 9.11 4 6.6 5.64 5.35 8.04 2.34 8.36 0 10.91 0 14c0 3.31 2.69 6 6 6h13c2.76 0 5-2.24 5-5 0-2.64-2.05-4.78-4.65-4.96z\"/></svg>");
        tech2.put("tags", List.of("AWS", "Azure", "GCP", "Kubernetes", "Docker"));
        technologies.add(tech2);
        
        Map<String, Object> tech3 = new HashMap<>();
        tech3.put("name", "Data Science");
        tech3.put("icon", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" width=\"48\" height=\"48\"><path fill=\"currentColor\" d=\"M19 3H5c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zM9 17H7v-7h2v7zm4 0h-2V7h2v10zm4 0h-2v-4h2v4z\"/></svg>");
        tech3.put("tags", List.of("Python", "TensorFlow", "PyTorch", "Big Data", "ML"));
        technologies.add(tech3);
        
        return ResponseEntity.ok(technologies);
    }
    
    @GetMapping("/industries")
    public ResponseEntity<List<Map<String, Object>>> getIndustries() {
        List<Map<String, Object>> industries = new ArrayList<>();
        
        Map<String, Object> industry1 = new HashMap<>();
        industry1.put("name", "Information Technology");
        industry1.put("icon", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" width=\"48\" height=\"48\"><path fill=\"currentColor\" d=\"M20 18c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2H4c-1.1 0-2 .9-2 2v10c0 1.1.9 2 2 2H0v2h24v-2h-4zM4 6h16v10H4V6z\"/></svg>");
        industries.add(industry1);
        
        Map<String, Object> industry2 = new HashMap<>();
        industry2.put("name", "Finance & Banking");
        industry2.put("icon", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" width=\"48\" height=\"48\"><path fill=\"currentColor\" d=\"M4 10v7h3v-7H4zm6 0v7h3v-7h-3zM2 22h19v-3H2v3zm14-12v7h3v-7h-3zm-4.5-9L2 6v2h19V6l-9.5-5z\"/></svg>");
        industries.add(industry2);
        
        Map<String, Object> industry3 = new HashMap<>();
        industry3.put("name", "Healthcare");
        industry3.put("icon", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" width=\"48\" height=\"48\"><path fill=\"currentColor\" d=\"M19 3H5c-1.1 0-1.99.9-1.99 2L3 19c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm-1 11h-4v4h-4v-4H6v-4h4V6h4v4h4v4z\"/></svg>");
        industries.add(industry3);
        
        return ResponseEntity.ok(industries);
    }
    
    @GetMapping("/stats")
    public ResponseEntity<List<Map<String, Object>>> getStats() {
        List<Map<String, Object>> stats = new ArrayList<>();
        
        Map<String, Object> stat1 = new HashMap<>();
        stat1.put("value", "500+");
        stat1.put("label", "Successful Projects");
        stat1.put("offset", "0");
        stats.add(stat1);
        
        Map<String, Object> stat2 = new HashMap<>();
        stat2.put("value", "95%");
        stat2.put("label", "Client Satisfaction");
        stat2.put("offset", "14");
        stats.add(stat2);
        
        Map<String, Object> stat3 = new HashMap<>();
        stat3.put("value", "200+");
        stat3.put("label", "Partner Companies");
        stat3.put("offset", "113");
        stats.add(stat3);
        
        Map<String, Object> stat4 = new HashMap<>();
        stat4.put("value", "10+");
        stat4.put("label", "Years Experience");
        stat4.put("offset", "198");
        stats.add(stat4);
        
        return ResponseEntity.ok(stats);
    }
    
    @GetMapping("/testimonials")
    public ResponseEntity<List<Map<String, Object>>> getTestimonials() {
        List<Map<String, Object>> testimonials = new ArrayList<>();
        
        Map<String, Object> testimonial1 = new HashMap<>();
        testimonial1.put("quote", "SSRM Tech helped us find the perfect development team in record time. Their understanding of our technical needs was impressive.");
        testimonial1.put("name", "Michael Johnson");
        testimonial1.put("title", "CTO, TechInnovate");
        testimonial1.put("image", "https://randomuser.me/api/portraits/men/32.jpg");
        testimonials.add(testimonial1);
        
        Map<String, Object> testimonial2 = new HashMap<>();
        testimonial2.put("quote", "The quality of candidates provided by SSRM Tech was exceptional. They truly understand the tech industry and its unique requirements.");
        testimonial2.put("name", "Sarah Williams");
        testimonial2.put("title", "HR Director, DataSystems Inc.");
        testimonial2.put("image", "https://randomuser.me/api/portraits/women/44.jpg");
        testimonials.add(testimonial2);
        
        Map<String, Object> testimonial3 = new HashMap<>();
        testimonial3.put("quote", "Working with SSRM Tech has transformed our hiring process. Their consultative approach and industry expertise are unmatched.");
        testimonial3.put("name", "David Chen");
        testimonial3.put("title", "CEO, CloudNative Solutions");
        testimonial3.put("image", "https://randomuser.me/api/portraits/men/67.jpg");
        testimonials.add(testimonial3);
        
        return ResponseEntity.ok(testimonials);
    }
}