javac -cp .;guava-19.0.jar Main.java


java -cp .;guava-19.0.jar Main -d 20 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\Netflix5k5K\copy1.train" -fnTestData ".\dataset\Netflix5k5K\copy1.test" -fnOutputData ".\dataset\result\Netflix5k5K_500_5000_1.txt" -n 5000 -m 5000 -num_iterations 500 -MinRating 1.0 -MaxRating 5.0 -k 5000 -insertions 100000 -fpp 0.00001

java -cp .;guava-19.0.jar Main -d 20 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\Netflix5k5K\copy2.train" -fnTestData ".\dataset\Netflix5k5K\copy2.test" -fnOutputData ".\dataset\result\Netflix5k5K_500_5000_2.txt" -n 5000 -m 5000 -num_iterations 500 -MinRating 1.0 -MaxRating 5.0 -k 5000 -insertions 100000 -fpp 0.00001

java -cp .;guava-19.0.jar Main -d 20 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\Netflix5k5K\copy3.train" -fnTestData ".\dataset\Netflix5k5K\copy3.test" -fnOutputData ".\dataset\result\Netflix5k5K_500_5000_3.txt" -n 5000 -m 5000 -num_iterations 500 -MinRating 1.0 -MaxRating 5.0 -k 5000 -insertions 100000 -fpp 0.00001

pause;