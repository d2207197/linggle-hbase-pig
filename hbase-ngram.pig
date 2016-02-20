A = load '/corpus/Web1T/*/*' using PigStorage as (ngram: chararray, count: int);
-- A = load '/corpus/Web1T/3gms/3gm-0090.gz' using PigStorage as (ngram: chararray, count: int);
-- A = load 'test_ngram_data' using PigStorage as (ngram: chararray, count: int);


REGISTER 'pig-hbase-ngram/target/scala-2.11/pig-hbase-ngram-assembly-1.0.jar'

-- register '/usr/lib/pig/datafu-1.1.0-cdh5.0.2.jar'

-- register '/home/external/joe/projects/pig-hbase-ngram/scala-pig/target/scala-2.10/Scala-Pig-assembly-1.0.jar'
-- A = load 'test_ngram_data' using PigStorage as (ngram: chararray, count: int);
-- B = limit A 15;
C = filter A by udf.ValidateNgram(ngram);
-- describe A;


D = foreach C generate flatten(udf.ToFramesSels(ngram)) as (frame: chararray, selector: chararray), ngram, count ;

-- limited_out = limit D 10;
-- describe limited_out
-- dump limited_out        

        -- E = group D by (frame, selector);
E = foreach (group D by (frame, selector)) generate group, D.(ngram, count) as D;
describe E;

F = foreach E {
        sorted = order D by count desc;
        ngrams    = limit sorted 1000;
        generate group, SUM(D.count) as sum, ngrams;
        }

describe F
-- dump F

store F into 'linggle-web1t';
        