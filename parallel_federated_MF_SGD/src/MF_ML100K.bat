javac -cp .;guava-19.0.jar Main.java


java -cp .;guava-19.0.jar Main -d 20 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\ML100K\u1.base" -fnTestData ".\dataset\ML100K\u1.test" -fnOutputData ".\dataset\result\ML100K_1000_94_1.txt" -n 943 -m 1682 -num_iterations 1000 -MinRating 1.0 -MaxRating 5.0 -k 94 -insertions 100000 -fpp 0.00001

java -cp .;guava-19.0.jar Main -d 20 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\ML100K\u2.base" -fnTestData ".\dataset\ML100K\u2.test" -fnOutputData ".\dataset\result\ML100K_1000_94_2.txt" -n 943 -m 1682 -num_iterations 1000 -MinRating 1.0 -MaxRating 5.0 -k 94 -insertions 100000 -fpp 0.00001

java -cp .;guava-19.0.jar Main -d 20 -alpha_u 0.01 -alpha_v 0.01 -gamma 0.01 -fnTrainData ".\dataset\ML100K\u3.base" -fnTestData ".\dataset\ML100K\u3.test" -fnOutputData ".\dataset\result\ML100K_1000_94_3.txt" -n 943 -m 1682 -num_iterations 1000 -MinRating 1.0 -MaxRating 5.0 -k 94 -insertions 100000 -fpp 0.00001

pause;