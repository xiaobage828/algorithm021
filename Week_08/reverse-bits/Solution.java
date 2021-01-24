public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        // 在二进制中交换两部分，可以用一个技巧，举个例子，对于 x = 1101 交换两部分，
        // 我们只需要(1100) & x >>> 2 | (0011) & x <<< (0011)|(0100)= 0111 ，然后就完成了 11 和 01 的交换。

        // 分治
        n = (n>>>16) | (n<<16);
        n = ((n&0xff00ff00)>>>8) | ((n&0x00ff00ff)<<8);
        n = ((n&0xf0f0f0f0)>>>4) | ((n&0x0f0f0f0f)<<4);
        n = ((n&0xcccccccc)>>>2) | ((n&0x33333333)<<2);
        n = ((n&0xaaaaaaaa)>>>1) | ((n&0x55555555)<<1);
        return n;
    }
}