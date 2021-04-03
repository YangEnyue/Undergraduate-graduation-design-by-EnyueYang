javac -cp .;guava-19.0.jar Main.java

java -cp .;guava-19.0.jar Main -d 5 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\Netflix5k5K\copy1.train" -fnTestData ".\dataset\Netflix5k5K\copy1.test" -fnOutputData ".\dataset\result\Netflix5k5K_d_5.txt" -n 5000 -m 5000 -num_iterations 500 -MinRating 1.0 -MaxRating 5.0 -k 5000 -insertions 100000 -fpp 0.00001

java -cp .;guava-19.0.jar Main -d 10 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\Netflix5k5K\copy1.train" -fnTestData ".\dataset\Netflix5k5K\copy1.test" -fnOutputData ".\dataset\result\Netflix5k5K_d_10.txt" -n 5000 -m 5000 -num_iterations 500 -MinRating 1.0 -MaxRating 5.0 -k 5000 -insertions 100000 -fpp 0.00001

java -cp .;guava-19.0.jar Main -d 15 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\Netflix5k5K\copy1.train" -fnTestData ".\dataset\Netflix5k5K\copy1.test" -fnOutputData ".\dataset\result\Netflix5k5K_d_15.txt" -n 5000 -m 5000 -num_iterations 500 -MinRating 1.0 -MaxRating 5.0 -k 5000 -insertions 100000 -fpp 0.00001

java -cp .;guava-19.0.jar Main -d 20 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\Netflix5k5K\copy1.train" -fnTestData ".\dataset\Netflix5k5K\copy1.test" -fnOutputData ".\dataset\result\Netflix5k5K_d_20.txt" -n 5000 -m 5000 -num_iterations 500 -MinRating 1.0 -MaxRating 5.0 -k 5000 -insertions 100000 -fpp 0.00001

java -cp .;guava-19.0.jar Main -d 25 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\Netflix5k5K\copy1.train" -fnTestData ".\dataset\Netflix5k5K\copy1.test" -fnOutputData ".\dataset\result\Netflix5k5K_d_25.txt" -n 5000 -m 5000 -num_iterations 500 -MinRating 1.0 -MaxRating 5.0 -k 5000 -insertions 100000 -fpp 0.00001


java -cp .;guava-19.0.jar Main -d 30 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\Netflix5k5K\copy1.train" -fnTestData ".\dataset\Netflix5k5K\copy1.test" -fnOutputData ".\dataset\result\Netflix5k5K_d_30.txt" -n 5000 -m 5000 -num_iterations 500 -MinRating 1.0 -MaxRating 5.0 -k 5000 -insertions 100000 -fpp 0.00001
pause;