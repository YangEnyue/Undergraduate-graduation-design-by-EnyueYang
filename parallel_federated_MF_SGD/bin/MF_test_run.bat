javac -cp .;guava-19.0.jar Main.java


java -cp .;guava-19.0.jar Main -d 20 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\ML1M\copy1.train" -fnTestData ".\dataset\ML1M\copy1.test" -fnOutputData ".\dataset\result\ML1M_1500_604_1.txt" -n 6040 -m 3952 -num_iterations 1500 -MinRating 1.0 -MaxRating 5.0 -k 604 -insertions 1000 -fpp 0.00001

java -cp .;guava-19.0.jar Main -d 20 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\ML1M\copy2.train" -fnTestData ".\dataset\ML1M\copy2.test" -fnOutputData ".\dataset\result\ML1M_1500_604_2.txt" -n 6040 -m 3952 -num_iterations 1500 -MinRating 1.0 -MaxRating 5.0 -k 604 -insertions 1000 -fpp 0.00001
java -cp .;guava-19.0.jar Main -d 20 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\ML1M\copy3.train" -fnTestData ".\dataset\ML1M\copy3.test" -fnOutputData ".\dataset\result\ML1M_1500_604_3.txt" -n 6040 -m 3952 -num_iterations 1500 -MinRating 1.0 -MaxRating 5.0 -k 604 -insertions 1000 -fpp 0.00001

pause;