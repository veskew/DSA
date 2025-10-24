# Strassen Multiply vs. Traditional Matrix Multiplication

I implemented Strassen's algorithm and traditional matrix multiplication, however traditional
matrix multiplication was consistently outperforming Strassen's algorithm at all matrix sizes.
In order to combat this, I modified the recursion logic in my code to call strassen multiply 
all the way down to matrices of size 32, and then I switched to normal matrix multiply.

| Matrix Size | Fully Strassen multiply | Traditional Multiply | Hybrid Strassen and traditional Multiplication |
|-------------|-------------------------|----------------------|------------------------------------------------|
| 2^3         | .000790                 | .000249              | .000343                                        |
| 2^5         | .00787                  | .000658              | .000666                                        |
| 2^7         | .157                    | .00759               | .0142                                          |
| 2^9         | 2.82                    | .135                 | .132                                           |
| 2^11        | 151.0                   | 49.5                 | 3.71                                           |

As evidenced by the table, the best strategy for matrix multiplication was clearly the hybrid
approach. I was really surprised to see exactly how much faster that approach ended up
being.