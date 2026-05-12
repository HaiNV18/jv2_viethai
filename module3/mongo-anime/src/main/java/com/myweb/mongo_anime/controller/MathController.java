package com.myweb.mongo_anime.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @GetMapping("/api/solve_quadratic")
    public ResponseEntity<double[]> solveQuadratic(
            @RequestParam Double a, @RequestParam Double b, @RequestParam Double c) {
        if (a == 0) throw new IllegalArgumentException("a cannot be 0");
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) new ResponseEntity<>(new double[2], HttpStatus.OK);
        if (discriminant == 0) new ResponseEntity<>(new double[] { -b / (2 * a) }, HttpStatus.OK);
        double sqrtD = Math.sqrt(discriminant);
        double[] results = new double[] {
                (-b + sqrtD) / (2 * a),
                (-b - sqrtD) / (2 * a)
        };
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

}
