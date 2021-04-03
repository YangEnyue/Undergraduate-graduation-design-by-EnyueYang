javac -cp .;guava-19.0.jar Main.java

java -cp .;guava-19.0.jar Main -d 5 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\ML1M\copy1.train" -fnTestData ".\dataset\ML1M\copy1.test" -fnOutputData ".\dataset\result\ML1M_d_5.txt" -n 6040 -m 3952 -num_iterations 1500 -MinRating 1.0 -MaxRating 5.0 -k 604 -insertions 1000 -fpp 0.00001

java -cp .;guava-19.0.jar Main -d 10 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\ML1M\copy1.train" -fnTestData ".\dataset\ML1M\copy1.test" -fnOutputData ".\dataset\result\ML1M_d_10.txt" -n 6040 -m 3952 -num_iterations 1500 -MinRating 1.0 -MaxRating 5.0 -k 604 -insertions 1000 -fpp 0.00001

java -cp .;guava-19.0.jar Main -d 15 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\ML1M\copy1.train" -fnTestData ".\dataset\ML1M\copy1.test" -fnOutputData ".\dataset\result\ML1M_d_15.txt" -n 6040 -m 3952 -num_iterations 1500 -MinRating 1.0 -MaxRating 5.0 -k 604 -insertions 1000 -fpp 0.00001

java -cp .;guava-19.0.jar Main -d 20 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\ML1M\copy1.train" -fnTestData ".\dataset\ML1M\copy1.test" -fnOutputData ".\dataset\result\ML1M_d_20.txt" -n 6040 -m 3952 -num_iterations 1500 -MinRating 1.0 -MaxRating 5.0 -k 604 -insertions 1000 -fpp 0.00001

java -cp .;guava-19.0.jar Main -d 25 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\ML1M\copy1.train" -fnTestData ".\dataset\ML1M\copy1.test" -fnOutputData ".\dataset\result\ML1M_d_25.txt" -n 6040 -m 3952 -num_iterations 1500 -MinRating 1.0 -MaxRating 5.0 -k 604 -insertions 1000 -fpp 0.00001

java -cp .;guava-19.0.jar Main -d 30 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\ML1M\copy1.train" -fnTestData ".\dataset\ML1M\copy1.test" -fnOutputData ".\dataset\result\ML1M_d_30.txt" -n 6040 -m 3952 -num_iterations 1500 -MinRating 1.0 -MaxRating 5.0 -k 604 -insertions 1000 -fpp 0.00001
pause;