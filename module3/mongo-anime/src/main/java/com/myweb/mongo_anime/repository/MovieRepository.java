package com.myweb.mongo_anime.repository;

import com.myweb.mongo_anime.dto.ChartDTO;
import com.myweb.mongo_anime.model.Movie;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    @Query("{ 'VoteAverage' : { $gte: ?0 } }")
    List<Movie> findHighlyRated(double minRating);

    @Aggregation(pipeline = {
            "{ $project: { month: { $substr: ['$Release_Date', 0, 7] } } }",
            "{ $group: { _id: '$month', total: { $sum: 1 } } }",
            "{ $sort: { total: -1 } }",
            "{ $limit: 7 }",
            "{ $project: { label: '$_id', value: '$total', _id: 0 } }"
    })
    List<ChartDTO> getTop7ReleaseMonths();

    @Aggregation(pipeline = {
            "{ $unwind: '$Genre' }",
            "{ $lookup: { from: 'genre', localField: 'Genre', foreignField: '_id', as: 'genreInfo' } }",
            "{ $unwind: '$genreInfo' }",

            "{ $group: { _id:  '$genreInfo.name', total:  {$sum:  1}}}",
            "{ $sort: { total: -1 } } ",
            "{ $project: { label: '$_id', value: '$total', _id: 0 } }"
    })
    List<ChartDTO> getMoviesByGenre();
    // $unwind: tach cac phan tu, vi ket qua la array []
    // $lookup: join voi collection genre. Ket qua duoc dat ten la genreInfo
    // $group: giong GROUP BY $genreInfo.name

    List<Movie> findTop5ByOrderByVoteAverageDesc(); // 5 phim danh gia cao nhat

    List<Movie> findTop5ByOrderByReleaseDateDesc(); // 5 phim phat hanh gan nhat

    List<Movie> findTop10ByOrderByVoteCountDesc(); // 10 phim pho bien nhat
}
